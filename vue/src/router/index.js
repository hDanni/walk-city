import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Locations from '../views/Locations.vue'
import LocationDetail from '../views/LocationDetail.vue'
import History from '../views/History.vue'
import Awards from '../views/Awards.vue'
import SearchLocations from '../views/SearchLocations.vue'
import AboutUs from '../views/AboutUs.vue'
import FAQ from '../views/FAQ.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      query: '',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/locations",
      name: "locations",
      component: Locations,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/locations/:locationId/detail",
      name: "locationDetail",
      component: LocationDetail,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/history",
      name: "history",
      component: History,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/awards",
      name: "awards",
      component: Awards,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/search/:searchTerm",
      name: "search",
      component: SearchLocations,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/aboutus",
      name: "aboutus",
      component: AboutUs,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/faq",
      name: "faq",
      component: FAQ,
      meta: {
        requiresAuth: false
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
