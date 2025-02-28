<script setup>
import { ref, onMounted, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

const store = useStore()
const router = useRouter()
const loading = ref(true)
const error = ref(null)
const isProfileLoaded = ref(false)
const showModal = ref(false)

const userProfile = computed(() => store.state.userProfile)

const hideEmail = (email) => {
  if (!email) return ''
  const [name, domain] = email.split('@')
  return `${name[0]}${new Array(name.length - 1).join('*')}@${domain}`
}

onMounted(async () => {
  try {
    loading.value = true
    await store.dispatch('fetchUserProfile')
    if (!userProfile.value || !userProfile.value.name) {
      // Если профиль не загружен или пустой, показываем модальное окно
      showModal.value = true
    } else {
      isProfileLoaded.value = true
    }
  } catch (e) {
    error.value = 'Failed to load profile data'
  } finally {
    loading.value = false
  }
})

const closeModal = () => {
  showModal.value = false
}


const goToRegistration = () => {
  router.push('/register')
}
</script>

<template>
  <div class="settings">
    <h1>Profile Settings</h1>

    <div v-if="loading" class="loading">Loading...</div>

    <div v-else-if="error" class="error">{{ error }}</div>

    <div v-else class="profile-data" v-if="isProfileLoaded">
      <div class="profile-item">
        <label>Name:</label>
        <span>{{ userProfile.name }}</span>
      </div>

      <div class="profile-item">
        <label>Email:</label>
        <span>{{ hideEmail(userProfile.email) }}</span>
      </div>

      <div class="profile-item">
        <label>Role:</label>
        <span>{{ userProfile.role }}</span>
      </div>
    </div>

    <!-- Modal for incomplete profile -->
    <div v-if="showModal" class="modal-overlay">
      <div class="modal">
        <h2 style="color: green;">You are not fully registered!</h2>
        <p style="color: green;">It looks like your profile is incomplete. Please complete your registration.</p>
        <button @click="goToRegistration">OK</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.settings {
  padding: 20px;
  max-width: 600px;
  margin: 0 auto;
}

.loading, .error {
  text-align: center;
  margin: 20px 0;
}

.error {
  color: red;
}

.profile-data {
  margin-top: 20px;
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.profile-item {
  display: flex;
  padding: 15px 0;
  border-bottom: 1px solid #000000;
}

.profile-item:last-child {
  border-bottom: none;
}

.profile-item label {
  width: 100px;
  font-weight: bold;
  color: #000000;
}

.profile-item span {
  flex: 1;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal {
  background: white;
  padding: 30px;
  border-radius: 8px;
  text-align: center;
  width: 300px;
}

.modal h2 {
  margin-bottom: 20px;
}

.modal button {
  background-color: #007BFF;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

.modal button:hover {
  background-color: #0056b3;
}
</style>
