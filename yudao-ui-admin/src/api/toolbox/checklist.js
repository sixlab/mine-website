import request from '@/utils/request'

// 创建任务清单
export function createChecklist(data) {
  return request({
    url: '/toolbox/checklist/create',
    method: 'post',
    data: data
  })
}

// 更新任务清单
export function updateChecklist(data) {
  return request({
    url: '/toolbox/checklist/update',
    method: 'put',
    data: data
  })
}

// 删除任务清单
export function deleteChecklist(id) {
  return request({
    url: '/toolbox/checklist/delete?id=' + id,
    method: 'delete'
  })
}

// 获得任务清单
export function getChecklist(id) {
  return request({
    url: '/toolbox/checklist/get?id=' + id,
    method: 'get'
  })
}

// 获得任务清单分页
export function getChecklistPage(query) {
  return request({
    url: '/toolbox/checklist/page',
    method: 'get',
    params: query
  })
}

// 导出任务清单 Excel
export function exportChecklistExcel(query) {
  return request({
    url: '/toolbox/checklist/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
