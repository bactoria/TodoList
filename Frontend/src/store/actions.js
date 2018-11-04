import axios from 'axios'
import Const from '../Constant'

var server = "http://localhost:8081/api"

export default {
    [Const.GET_TODO_ALL] : (store) => {
        axios.get(server + "/todos")
            .then((response) => {
                store.commit(Const.GET_TODO_ALL, response.data)
            })
    },

    [Const.GET_TODO_EXPIRED] : (store) => {
        axios.get(server + "/todos/expired")
            .then((response) => {
                store.commit(Const.GET_TODO_EXPIRED, response.data)
            })
    },

    [Const.ADD_TODO] : (store, payload) => {
        axios.post(server + "/todos", payload)
            .then(() => {
                store.dispatch(Const.GET_TODO_ALL)
                store.commit(Const.INIT_ADD_MODAL)
                store.commit(Const.DISABLE_ADD_MODAL)
            })
            .catch(error => {
                alert(error.response.data.message)
            })
    },

    [Const.COMPLETED_TODO] : (store, payload) => {
        axios.put(payload.href + '/completed')
            .then(() => {
                store.dispatch(Const.GET_TODO_ALL)
            })
    },

    [Const.DELETE_TODO] : (store, payload) => {
        axios.delete(payload.href)
            .then(() => {
                store.dispatch(Const.GET_TODO_ALL)
            })
    },

    [Const.UPDATE_TODO] : (store, payload) => {
        axios.put(payload.link, payload.post)
            .then(()=> {
                store.dispatch(Const.GET_TODO_ALL)
                store.commit(Const.DISABLE_UPDATE_MODAL)
            })
            .catch(error => {
                alert(error.response.data.message)
            })
    }
}
