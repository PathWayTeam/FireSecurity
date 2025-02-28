<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { auth } from '@/services/api'

const router = useRouter()
const name = ref('')
const email = ref('')
const password = ref('')
const confirmPassword = ref('')
const errorMessage = ref('')

const handleSubmit = async () => {
  if (password.value !== confirmPassword.value) {
    errorMessage.value = 'Passwords do not match!'
    return
  }

  try {
    const response = await auth.register({
      username: name.value,
      email: email.value,
      password: password.value,
      description: '',
      avatarUrl: ''
    })

    localStorage.setItem('token', response.data)
    router.push('/profile')
  } catch (err) {
    errorMessage.value = 'Registration failed'
  }
}
</script>

<template>
  <div class="register">
    <div class="register-container">
      <h1>Register</h1>
      <div v-if="errorMessage" class="error">{{ errorMessage }}</div>

      <div class="form-group">
        <label for="name">Name</label>
        <input type="name" id="name" v-model="name"/>
      </div>

      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" v-model="email"/>
      </div>

      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="password"/>
      </div>

      <div class="form-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword"/>
      </div>

      <button @click="handleSubmit">Register</button>
    </div>
  </div>
</template>

<style scoped>
.register {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f5f5f5;
  padding: 20px;
}

.register-container {
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
  transition: all 0.2s ease;
}

button:hover {
  background-color: #333;
}

button:active {
  transform: scale(0.98);
}

.error {
  color: #000;
  text-align: center;
  margin-bottom: 24px;
  padding: 12px;
  background-color: #f5f5f5;
  border: 1px solid #000;
  border-radius: 8px;
  font-size: 14px;
}

@media (max-width: 480px) {
  .register-container {
    padding: 24px;
  }

  h1 {
    font-size: 24px;
  }

  .form-group {
    margin-bottom: 16px;
  }
}
</style>
