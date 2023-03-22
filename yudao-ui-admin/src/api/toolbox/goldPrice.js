import request from '@/utils/request'

// 创建金价监控
export function createGoldPrice(data) {
  return request({
    url: '/toolbox/gold-price/create',
    method: 'post',
    data: data
  })
}

// 更新金价监控
export function updateGoldPrice(data) {
  return request({
    url: '/toolbox/gold-price/update',
    method: 'put',
    data: data
  })
}

// 删除金价监控
export function deleteGoldPrice(id) {
  return request({
    url: '/toolbox/gold-price/delete?id=' + id,
    method: 'delete'
  })
}

// 获得金价监控
export function getGoldPrice(id) {
  return request({
    url: '/toolbox/gold-price/get?id=' + id,
    method: 'get'
  })
}

// 获得金价监控分页
export function getGoldPricePage(query) {
  return request({
    url: '/toolbox/gold-price/page',
    method: 'get',
    params: query
  })
}

// 导出金价监控 Excel
export function exportGoldPriceExcel(query) {
  return request({
    url: '/toolbox/gold-price/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
