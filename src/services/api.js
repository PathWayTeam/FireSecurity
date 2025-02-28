import axios from 'axios';

2
const api = axios.create({
    baseURL: 'http://localhost:8080/api/v1',
});

api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});


export const auth = {
    login: (credentials) => api.post('/auth/login', credentials),
    register: (userData) => api.post('/auth/register', userData),
};

export const waypoints = {
    getAll: () => api.get('/waypoints/getAll'),
    search: (name) => api.get(`/waypoints/search?name=${name}`),
    add: (waypoint) => api.post('/waypoints/add', waypoint),
};

export const reports = {
    getAll: () => api.get('/reports/getAll'),
    search: (name) => api.get(`/reports/search?name=${name}`),
    send: (report) => api.post('/reports/send', report),
};

export const users = {
    getAll: () => api.get('/users/getAll'),
    getById: (id) => api.get(`/users/${id}`),
    updateProfile: (userData) => api.put('/users/updateProfile', userData),
    getUserProfile: () => api.get('/users/profile'),
};

export default api;
