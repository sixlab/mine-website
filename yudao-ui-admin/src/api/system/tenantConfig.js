import request from '@/utils/request'

// 创建租户参数配置
export function createTenantConfig(data) {
  return request({
    url: '/system/tenant-config/create',
    method: 'post',
    data: data
  })
}

// 更新租户参数配置
export function updateTenantConfig(data) {
  return request({
    url: '/system/tenant-config/update',
    method: 'put',
    data: data
  })
}

// 删除租户参数配置
export function deleteTenantConfig(id) {
  return request({
    url: '/system/tenant-config/delete?id=' + id,
    method: 'delete'
  })
}

// 获得租户参数配置
export function getTenantConfig(id) {
  return request({
    url: '/system/tenant-config/get?id=' + id,
    method: 'get'
  })
}

// 获得租户参数配置分页
export function getTenantConfigPage(query) {
  return request({
    url: '/system/tenant-config/page',
    method: 'get',
    params: query
  })
}

// 导出租户参数配置 Excel
export function exportTenantConfigExcel(query) {
  return request({
    url: '/system/tenant-config/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
