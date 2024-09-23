
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import BookBookManager from "./components/listers/BookBookCards"
import BookBookDetail from "./components/listers/BookBookDetail"

import UserUserManager from "./components/listers/UserUserCards"
import UserUserDetail from "./components/listers/UserUserDetail"

import BorrowingBorrowingManager from "./components/listers/BorrowingBorrowingCards"
import BorrowingBorrowingDetail from "./components/listers/BorrowingBorrowingDetail"



export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/books/books',
                name: 'BookBookManager',
                component: BookBookManager
            },
            {
                path: '/books/books/:id',
                name: 'BookBookDetail',
                component: BookBookDetail
            },

            {
                path: '/users/users',
                name: 'UserUserManager',
                component: UserUserManager
            },
            {
                path: '/users/users/:id',
                name: 'UserUserDetail',
                component: UserUserDetail
            },

            {
                path: '/borrowings/borrowings',
                name: 'BorrowingBorrowingManager',
                component: BorrowingBorrowingManager
            },
            {
                path: '/borrowings/borrowings/:id',
                name: 'BorrowingBorrowingDetail',
                component: BorrowingBorrowingDetail
            },




    ]
})
