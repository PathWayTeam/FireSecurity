<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { waypoints, reports } from '@/services/api'

const router = useRouter()
const userData = ref({
  waypoints: [],
  reports: []
})
const loading = ref(false)
const error = ref(null)

onMounted(async () => {
  const token = localStorage.getItem('token')
  if (!token) {
    router.push('/login')
    return
  }

  loading.value = true
  try {
    const [waypointsRes, reportsRes] = await Promise.all([
      waypoints.getAll(),
      reports.getAll()
    ])
    userData.value = {
      waypoints: waypointsRes.data,
      reports: reportsRes.data
    }
  } catch (err) {
    error.value = 'Failed to load data'
  } finally {
    loading.value = false
  }
})
</script>

<template>
  <div class="profile">
    <div v-if="loading">Loading...</div>
    <div v-else-if="error" class="error">{{ error }}</div>
    <div v-else class="profile-content">
      <section class="waypoints-section">
        <h2>My Waypoints</h2>
        <div class="waypoints-grid">
          <div v-for="waypoint in userData.waypoints" :key="waypoint.id" class="waypoint-card">
            <h3>{{ waypoint.address }}</h3>
            <div class="reports-list">
              <div v-for="report in waypoint.reports" :key="report.id" class="report-item">
                <span>{{ report.name }}</span>
                <div class="progress">
                  <div class="progress-bar" :style="{ width: report.procent + '%' }">
                    {{ report.procent }}%
                  </div>
                </div>
                <p>{{ report.text }}</p>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>
<style scoped>
.profile {
  min-height: 100vh;
  background-color: #f5f5f5;
  padding: 2rem;
}

.profile-content {
  max-width: 1200px;
  margin: 0 auto;
}

.waypoints-section {
  background: white;
  border-radius: 12px;
  padding: 2rem;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

h2 {
  color: #000;
  font-size: 24px;
  font-weight: 600;
  margin-bottom: 2rem;
  border-bottom: 2px solid #e0e0e0;
  padding-bottom: 1rem;
}

.waypoints-grid {
  display: grid;
  gap: 2rem;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
}

.waypoint-card {
  background: #fff;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 1.5rem;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.waypoint-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

h3 {
  color: #000;
  font-size: 18px;
  font-weight: 500;
  margin-bottom: 1rem;
}

.reports-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.report-item {
  background: #f8f8f8;
  border-radius: 6px;
  padding: 1rem;
}

.report-item span {
  display: block;
  font-weight: 500;
  color: #000;
  margin-bottom: 0.5rem;
}

.progress {
  background: #e0e0e0;
  border-radius: 4px;
  height: 8px;
  margin: 0.5rem 0;
  overflow: hidden;
}

.progress-bar {
  background: #000;
  height: 100%;
  transition: width 0.3s ease;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 10px;
}

.report-item p {
  color: #333;
  font-size: 14px;
  margin-top: 0.5rem;
}

.loading {
  text-align: center;
  padding: 2rem;
  color: #000;
  font-size: 18px;
}

.error {
  background: #fef2f2;
  color: #000;
  padding: 1rem;
  border-radius: 8px;
  text-align: center;
  margin: 1rem 0;
  border: 1px solid #e0e0e0;
}

@media (max-width: 768px) {
  .profile {
    padding: 1rem;
  }

  .waypoints-grid {
    grid-template-columns: 1fr;
  }

  .waypoint-card {
    padding: 1rem;
  }
}
</style>