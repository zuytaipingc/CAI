import request from '@/utils/request'

// 查询借用信息列表
export function listBorrow(query) {
  return request({
    url: '/device/borrow/list',
    method: 'get',
    params: query
  })
}

// 查询借用信息详细
export function getBorrow(borrowId) {
  return request({
    url: '/device/borrow/' + borrowId,
    method: 'get'
  })
}

// 新增借用信息
export function addBorrow(data) {
  return request({
    url: '/device/borrow',
    method: 'post',
    data: data
  })
}

// 修改借用信息
export function updateBorrow(data) {
  return request({
    url: '/device/borrow',
    method: 'put',
    data: data
  })
}

// 删除借用信息
export function delBorrow(borrowId) {
  return request({
    url: '/device/borrow/' + borrowId,
    method: 'delete'
  })
}
