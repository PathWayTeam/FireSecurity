import { createStore } from 'vuex'
import axios from 'axios'

export default createStore({
    state: {
        reports: [],
        mapPoints: [],
        reportData: {
            total: 0,
            percentage: 0
        }
    },
    mutations: {
        SET_REPORTS(state, reports) {
            state.reports = reports
        },
        SET_MAP_POINTS(state, points) {
            state.mapPoints = points
        },
        SET_REPORT_DATA(state, data) {
            state.reportData = data
        }
    },
    actions: {
        async fetchReports({ commit }) {
            try {
                const response = await axios.get('/api/reports')
                commit('SET_REPORTS', response.data)

                // Calculate total and percentage
                if (response.data.length > 0) {
                    const total = response.data.length
                    const percentage = response.data.reduce((acc, report) =>
                        acc + (report.percentage || 0), 0) / total
                    commit('SET_REPORT_DATA', { total, percentage })
                }
            } catch (error) {
                console.error('Error fetching reports:', error)
                throw error
            }
        },
        async fetchMapPoints({ commit }) {
            try {
                const response = await axios.get('/api/map-points')
                commit('SET_MAP_POINTS', response.data)
            } catch (error) {
                console.error('Error fetching map points:', error)
                throw error
            }
        }
    },
    getters: {
        reportPercentage: state => state.reportData.percentage,
        totalReports: state => state.reportData.total
    }
})
