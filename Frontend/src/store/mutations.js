import Const from '../Constant'

export default {
    [Const.GET_TODO_ALL] : (state, payload) => {
        state.todoList = payload._embedded.todoResourceList;
    },
    [Const.GET_TODO_EXPIRED] : (state, payload) => {
        state.todoListExpired = payload._embedded.todoResourceList;
    },
    [Const.ENABLE_ADD_MODAL] : (state) => {
        state.addModalStatus = true
    },
    [Const.DISABLE_ADD_MODAL] : (state) => {
        state.addModalStatus = false
    },
    [Const.ENABLE_UPDATE_MODAL] : (state) => {
        state.updateModalStatus = true
    },
    [Const.DISABLE_UPDATE_MODAL] : (state) => {
        state.updateModalStatus = false
    },
    [Const.INIT_ADD_MODAL] : (state) => {
        state.addModal.title=''
        state.addModal.content=''
        state.addModal.priority=1
        state.addModal.closingDate= null
    }
}