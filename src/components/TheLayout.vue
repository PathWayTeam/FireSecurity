<script setup>
import {ref, onMounted} from 'vue'
import {useWindowSize} from '@vueuse/core'
import { useRouter } from 'vue-router'

const router = useRouter()
const {width} = useWindowSize()
const isAuthenticated = ref(false)

const toggleProfile = () => {
  router.push('/profile')
}
</script>

<template>
  <div class="app-layout">
    <header class="header" :class="{ 'mobile': width < 768 }">
      <nav>
        <router-link to="/" class="nav-link">Map</router-link>
        <router-link to="/reports" class="nav-link">Reports</router-link>
        <router-link to="/settings" class="nav-link">Settings</router-link>
        <div class="search">
          <input type="text" placeholder="Search locations...">
        </div>
        <button @click="toggleProfile" class="profile-btn">Profile</button>
      </nav>
      <main>
      <router-view v-slot="{ Component }">
        <transition name="fade" mode="out-in">
          <component :is="Component"/>
        </transition>
      </router-view>
      </main>

      <Teleport to="body">
        <div v-if="showProfileModal" class="modal" @click.self="toggleProfile">
          <div class="modal-content">
            <h3>{{ isAuthenticated ? 'Profile' : 'Please Login' }}</h3>
            <button @click="toggleProfile" class="close-btn">Close</button>
          </div>
        </div>
      </Teleport>
    </header>

  </div>
</template>

<style scoped>
.app-layout {
  min-height: 100vh;
  background-color: #fff;
}

.header {
  background: #000;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
  position: fixed;
  width: 100%;
  top: 0;
  z-index: 1000;
}

.main-content {
  padding-top: 80px;
  min-height: calc(100vh - 80px);
}

nav {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1rem;
  display: flex;
  align-items: center;
  gap: 2rem;
}

.nav-link {
  color: #fff;
  text-decoration: none;
  font-weight: 500;
  font-size: 0.95rem;
  padding: 0.5rem 0;
  border-bottom: 2px solid transparent;
  transition: all 0.2s ease;
}

.nav-link:hover {
  border-bottom-color: #fff;
}

.nav-link.router-link-active {
  border-bottom-color: #fff;
}

.search {
  flex: 1;
  max-width: 400px;
}

.search input {
  width: 100%;
  padding: 0.75rem 1rem;
  border: 1px solid #333;
  border-radius: 4px;
  background: #fff;
  color: #000;
  font-size: 0.9rem;
  transition: all 0.2s ease;
}

.search input:focus {
  outline: none;
  border-color: #666;
  box-shadow: 0 0 0 2px rgba(255, 255, 255, 0.1);
}

.search input::placeholder {
  color: #666;
}

.profile-btn {
  background: #fff;
  color: #000;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 4px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s ease;
}

.profile-btn:hover {
  background: #f0f0f0;
  transform: translateY(-1px);
}

.modal {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.8);
  display: grid;
  place-items: center;
  backdrop-filter: blur(3px);
}

.modal-content {
  background: #fff;
  padding: 2rem;
  border-radius: 8px;
  min-width: 320px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
}

.modal-content h3 {
  color: #000;
  margin-bottom: 1.5rem;
  font-size: 1.25rem;
  font-weight: 600;
}

.close-btn {
  width: 100%;
  padding: 0.75rem;
  background: #000;
  color: #fff;
  border: none;
  border-radius: 4px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s ease;
}

.close-btn:hover {
  background: #333;
}

@media (max-width: 768px) {
  .header {
    position: fixed;
    bottom: 0;
    top: auto;
  }

  .main-content {
    padding-top: 1rem;
    padding-bottom: 80px;
  }

  nav {
    padding: 0.75rem;
    gap: 1rem;
  }

  .search {
    display: none;
  }

  .profile-btn {
    padding: 0.5rem 1rem;
  }
}
</style>