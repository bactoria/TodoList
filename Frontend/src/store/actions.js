import axios from 'axios'
import Const from '../Constant'

export default {
    [Const.GET_TODO_ALL] : (store) => {
        axios.get("http://localhost:8081/api/todos")
            .then((response) => {
                store.commit(Const.GET_TODO_ALL, response.data)
            })
    },

    [Const.ADD_TODO] : (store, payload) => {
        axios.post("http://localhost:8081/api/todos", payload)
            .then(() => {
                store.dispatch(Const.GET_TODO_ALL)
                store.commit(Const.DISABLE_ADD_MODAL)
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
    }
}
