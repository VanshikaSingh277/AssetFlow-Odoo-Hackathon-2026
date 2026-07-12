<template>
  <div>
    <h2>📦 Assets</h2>
    <button @click="fetchAssets">Refresh</button>
    <button @click="addDummy">+ Add Dummy</button>

    <table border="1" cellpadding="8" style="margin-top: 12px; width: 100%;">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Serial</th>
          <th>Model</th>
          <th>Status</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="assets.length === 0">
          <td colspan="6">No assets yet. Add one!</td>
        </tr>
        <tr v-for="asset in assets" :key="asset.id">
          <td>{{ asset.id }}</td>
          <td>{{ asset.name }}</td>
          <td>{{ asset.serialNumber }}</td>
          <td>{{ asset.model }}</td>
          <td>{{ asset.status }}</td>
          <td>
            <button @click="deleteAsset(asset.id)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import api from '../api/axiosInstance';

interface Asset {
  id?: number;
  name: string;
  serialNumber: string;
  model: string;
  status: string;
}

const assets = ref<Asset[]>([]);

const fetchAssets = async () => {
  try {
    const res = await api.get('/assets');
    assets.value = res.data;
  } catch (e) {
    console.error(e);
    alert('Failed to fetch assets. Make sure you are logged in.');
  }
};

const addDummy = async () => {
  try {
    await api.post('/assets', {
      name: 'Dummy-' + Date.now(),
      serialNumber: 'SN-' + Math.random().toString(36).substring(2, 6),
      model: 'Model-X',
      status: 'Available'
    });
    await fetchAssets();
  } catch (e) {
    console.error(e);
    alert('Failed to add asset.');
  }
};

const deleteAsset = async (id: number) => {
  if (!confirm('Delete this asset?')) return;
  try {
    await api.delete(`/assets/${id}`);
    await fetchAssets();
  } catch (e) {
    console.error(e);
    alert('Failed to delete asset.');
  }
};

onMounted(fetchAssets);
</script>