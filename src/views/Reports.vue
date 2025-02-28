<script setup>
import { ref, onMounted } from 'vue';
import { reports } from '@/services/api';

const reportsList = ref([]);
const loading = ref(false);
const error = ref(null);
const searchQuery = ref('');
const noResults = ref(false);

const fetchReports = async () => {
  loading.value = true;
  try {
    const response = await reports.getAll();
    reportsList.value = response.data;
    noResults.value = reportsList.value.length === 0;
  } catch (err) {
    error.value = 'Не удалось загрузить отчеты';
  } finally {
    loading.value = false;
  }
};

const searchReports = async () => {
  if (!searchQuery.value) {
    await fetchReports();
    return;
  }

  loading.value = true;
  try {
    const response = await reports.search(searchQuery.value);
    reportsList.value = response.data;
    noResults.value = reportsList.value.length === 0;
  } catch (err) {
    error.value = 'Отчет не найден';
    reportsList.value = [];
    noResults.value = true;
  } finally {
    loading.value = false;
  }
};

onMounted(fetchReports);
</script>

<template>
  <div class="reports">
    <h2>Отчеты</h2>

    <div class="search-container">
      <input
          type="text"
          v-model="searchQuery"
          placeholder="Поиск отчетов..."
          @keyup.enter="searchReports"
      />
      <button @click="searchReports">Поиск</button>
    </div>

    <div v-if="loading" class="loading">Загрузка...</div>
    <div v-else-if="error" class="error">{{ error }}</div>
    <div v-else>
      <div v-if="noResults" class="no-results">Нет отчётов для отображения</div>
      <div class="reports-grid">
        <div v-for="report in reportsList" :key="report.id" class="report-card">
          <div class="report-header">
            <h3>{{ report.name }}</h3>
          </div>
          <div class="report-content">
            <p class="report-text">{{ report.text }}</p>
            <div class="progress-container">
              <span class="progress-label">Риск возгарания:</span>
              <div class="progress">
                <div
                    class="progress-bar"
                    :style="{ width: report.procent + '%' }"
                    :class="{
                    safe: report.procent < 30,
                    warning: report.procent >= 30 && report.procent < 60,
                    danger: report.procent >= 60,
                  }"
                >
                  {{ report.procent }}%
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.reports {
  min-height: 100vh;
  background-color: #f5f5f5;
  padding: 2rem;
}

h2 {
  color: #000;
  font-size: 28px;
  font-weight: 600;
  margin-bottom: 2rem;
  text-align: center;
}

.search-container {
  max-width: 600px;
  margin: 0 auto 2rem;
  display: flex;
  gap: 1rem;
}

.search-container input {
  flex: 1;
  padding: 12px;
  border: 2px solid #e0e0e0;
  border-radius: 8px;
  font-size: 15px;
}

.search-container button {
  padding: 12px 24px;
  background: #000;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.search-container button:hover {
  background: #333;
}

.reports-grid {
  display: grid;
  gap: 2rem;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  max-width: 1200px;
  margin: 0 auto;
}

.report-card {
  background: white;
  border: 1px solid #e0e0e0;
  border-radius: 12px;
  overflow: hidden;
  transition: transform 0.2s ease;
}

.report-card:hover {
  transform: translateY(-2px);
}

.report-header {
  padding: 1rem;
  border-bottom: 1px solid #e0e0e0;
  background: #f8f8f8;
}

.report-header h3 {
  margin: 0;
  color: #000;
  font-size: 18px;
  font-weight: 500;
}

.report-content {
  padding: 1rem;
}

.report-text {
  color: #333;
  font-size: 14px;
  line-height: 1.6;
  margin-bottom: 1rem;
}

.progress-container {
  margin-top: 1rem;
}

.progress-label {
  display: block;
  font-size: 14px;
  color: #666;
  margin-bottom: 0.5rem;
}

.progress {
  background: #e0e0e0;
  border-radius: 4px;
  height: 8px;
  overflow: hidden;
}

.progress-bar {
  height: 100%;
  transition: width 0.3s ease;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 10px;
  color: white;
  text-shadow: 0 0 2px rgba(0, 0, 0, 0.5);
}

.progress-bar.safe {
  background: #22c55e;
}

.progress-bar.warning {
  background: #eab308;
}

.progress-bar.danger {
  background: #ef4444;
}

.loading {
  text-align: center;
  padding: 2rem;
  color: #000;
  font-size: 18px;
}

.error {
  max-width: 400px;
  margin: 2rem auto;
  text-align: center;
  padding: 1rem;
  background: #fff;
  border: 1px solid #000;
  border-radius: 8px;
  color: #000;
}

.no-results {
  text-align: center;
  color: #777;
  font-size: 16px;
}

@media (max-width: 768px) {
  .reports {
    padding: 1rem;
  }

  .search-container {
    flex-direction: column;
  }

  .reports-grid {
    grid-template-columns: 1fr;
  }

  h2 {
    font-size: 24px;
    margin-bottom: 1.5rem;
  }
}
</style>
