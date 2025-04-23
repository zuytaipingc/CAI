import request from '@/utils/request'

// 查询app用户列表
export function listRegister(query) {
  return request({
    url: '/device/register/list',
    method: 'get',
    params: query
  })
}

// 查询app用户详细
export function getRegister(regId) {
  return request({
    url: '/device/register/' + regId,
    method: 'get'
  })
}

// 新增app用户
export function addRegister(data) {
  return request({
    url: '/device/register',
    method: 'post',
    data: data
  })
}

// 修改app用户
export function updateRegister(data) {
  return request({
    url: '/device/register',
    method: 'put',
    data: data
  })
}

// 删除app用户
export function delRegister(regId) {
  return request({
    url: '/device/register/' + regId,
    method: 'delete'
  })
}
