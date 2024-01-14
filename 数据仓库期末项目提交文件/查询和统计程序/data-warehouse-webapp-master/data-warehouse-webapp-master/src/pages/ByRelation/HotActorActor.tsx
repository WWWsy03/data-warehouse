import { PageContainer, ProForm, ProFormInstance, ProFormText } from '@ant-design/pro-components'
import { Alert, Card, Empty, List, message, Table } from 'antd'
import { useRef, useState } from 'react'
import QueryResult from '@/components/QueryResult/QueryResult'
import QueryLog from '@/components/QueryResult/QueryLog'
import { queryByDirectorName, queryByHotActorActor, queryMovieByCategory } from '@/services/data-warehouse/api'

type QueryParam = { category: string }

export default function ByCategory() {
  const formRef = useRef<ProFormInstance<QueryParam>>()

  const [queryResult, setQueryResult] = useState<API.QueryResult<API.HotAA[]> | null>(null)

  const query = async () => {
    const data = await formRef.current?.validateFields()
    if (data === undefined || data?.category === undefined) {
      message.warning('有必填字段未填')
      return
    }
    const resp = await queryByHotActorActor(data.category)
    if (resp.success) {
      setQueryResult(resp)
    }
  }

  return (
    <PageContainer>
      <div className={'flex flex-col gap-5'}>
        <Card>
          <ProForm<QueryParam> formRef={formRef} onFinish={query} autoFocusFirstInput>
            <ProForm.Group title={'查询参数'}>
              <ProFormText required name="category" label="电影类型" />
            </ProForm.Group>
          </ProForm>
        </Card>

        {(queryResult !== null && (
          <>
            <Alert message="查询成功" type="success" showIcon />

            <QueryResult result={queryResult}>
              <div className={'py-3'}>
                <Table
                  dataSource={queryResult.data}
                  columns={[
                    { title: '演员1', dataIndex: 'actor1' },
                    { title: '演员2', dataIndex: 'actor2' },
                  ]}
                />
              </div>
            </QueryResult>

            <QueryLog result={queryResult} />
          </>
        )) || (
          <Card>
            <Empty description="在您获得结果后，会显示在这里" />
          </Card>
        )}
      </div>
    </PageContainer>
  )
}
