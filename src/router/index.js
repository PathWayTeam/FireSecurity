import {createRouter, createWebHistory} from 'vue-router'
import MapView from '../views/MapView.vue'
import Reports from '../views/Reports.vue'
import Settings from '../views/Settings.vue'
import Register from "@/views/Register.vue";
import Profile from "@/views/Profile.vue";
import Login from "@/views/Login.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'map',
            component: MapView
        },
        {
            path: '/reports',
            name: 'reports',
            component: Reports
        },
        {
            path: '/settings',
            name: 'settings',
            component: Settings
        }, {
            path: '/register',
            name: 'Register',
            component: Register
        },{
            path: '/profile',
            name: 'profile',
            component: Profile
        },
        {
            path: '/login',
            name: 'login',
            component: Login
        }
    ]
})

export default router