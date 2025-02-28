<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { auth } from '@/services/api'

const router = useRouter()
const username = ref('')
const password = ref('')
const errorMessage = ref('')

const handleLogin = async () => {
  errorMessage.value = ''
  try {
    const response = await auth.login({
      username: username.value,
      password: password.value
    })


    if (response && response.data && response.status === 200) {
      localStorage.setItem('token', response.data.token)
      router.push('/profile')
    } else {
      errorMessage.value = 'Invalid response from server'
    }
  } catch (err) {
    console.error('Login Error:', err)
    errorMessage.value = 'Login failed. Please check your credentials.'
  }
}
</script>

<template>
  <div class="login">
    <div class="login-container">
      <h1>Login</h1>

      <div v-if="errorMessage" class="error">
        {{ errorMessage }}
      </div>

      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="username"/>
      </div>

      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="password"/>
      </div>

      <!-- Кнопка для логина -->
      <button @click="handleLogin">Login</button>
    </div>
  </div>
</template>

<style scoped>
.login {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f5f5f5;
  padding: 20px;
}

.login-container {
  width: 100%;
  max-width: 420px;
  background: white;
  padding: 40px;
  border-radius: 12px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  margin-bottom: 32px;
  color: #000;
  font-size: 28px;
  font-weight: 600;
  letter-spacing: -0.5px;
}

.form-group {
  margin-bottom: 24px;
}

label {
  display: block;
  margin-bottom: 8px;
  font-weight: 500;
  color: #1a1a1a;
  font-size: 14px;
}

input {
  width: 100%;
  padding: 12px;
  background: white;
  border: 2px solid #e0e0e0;
  border-radius: 8px;
  font-size: 15px;
  color: #1a1a1a;
  transition: border-color 0.2s ease;
}

input:focus {
  outline: none;
  border-color: #000;
}

input::placeholder {
  color: #9e9e9e;
}

button {
  width: 100%;
  padding: 14px;
  background-color: #000;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  transition: background-color 0.2s ease;
}

button:hover {
  background-color: #333;
}

button:active {
  transform: scale(0.98);
}

.error {
  color: #d32f2f;
  text-align: center;
  margin-bottom: 24px;
  padding: 12px;
  background-color: #fef2f2;
  border-radius: 8px;
  font-size: 14px;
}
</style>
