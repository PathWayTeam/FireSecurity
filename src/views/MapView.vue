<script setup>
import { onMounted, ref } from 'vue'
import 'leaflet/dist/leaflet.css'
import L from 'leaflet'
import { useStore } from 'vuex'

const map = ref(null)
const store = useStore()

const getMarkerColor = (percentage) => {
  if (percentage > 80) return 'red'
  if (percentage >= 50) return 'orange'
  return 'green'
}

onMounted(() => {
  map.value = L.map('map').setView([51.505, -0.09], 13)

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors'
  }).addTo(map.value)

  store.dispatch('fetchMapPoints').then(() => {
    const mapPoints = store.state.mapPoints

    mapPoints.forEach(point => {
      const { latitude, longitude, percentage } = point
      const color = getMarkerColor(percentage)

      const circle = L.circleMarker([latitude, longitude], {
        radius: 8,
        fillColor: color,
        color: color,
        weight: 2,
        opacity: 1,
        fillOpacity: 0.6
      }).addTo(map.value)

      circle.bindPopup(`Возгораемость: ${percentage}%`)
    })
  })
})
</script>

<template>
  <div id="map" style="height: calc(100vh - 60px);"></div>
</template>
