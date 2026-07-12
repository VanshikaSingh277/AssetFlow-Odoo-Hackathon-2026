<template>
  <div>
    <div v-if="!token">
      <h2>🔐 Login / Register</h2>
      <form @submit.prevent>
        <input v-model="username" placeholder="Username" />
        <input v-model="password" type="password" placeholder="Password" />
        <button @click="handleLogin">Login</button>
        <button @click="handleRegister">Register</button>
      </form>
      <p>{{ message }}</p>
    </div>
    <div v-else>
      <AssetList />
      <button @click="logout">Logout</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import api from './api/axiosInstance';
import AssetList from './components/AssetList.vue';

const username = ref('');
const password = ref('');
const message = ref('');

const token = computed(() => localStorage.getItem('token'));

const handleLogin = async () => {
  try {
    const res = await api.post('/auth/login', {
      username: username.value,
      password: password.value
    });
    localStorage.setItem('token', res.data);
    message.value = '✅ Login successful!';
    // Refresh page to show dashboard (optional)
    window.location.reload();
  } catch (err: any) {
    message.value = '❌ Login failed: ' + (err.response?.data || err.message);
  }
};

const handleRegister = async () => {
  try {
    await api.post('/auth/register', {
      username: username.value,
      password: password.value
    });
    message.value = '✅ User registered! Now login.';
  } catch (err: any) {
    message.value = '❌ Registration failed: ' + (err.response?.data || err.message);
  }
};

const logout = () => {
  localStorage.removeItem('token');
  message.value = 'Logged out.';
  window.location.reload();
};
</script>