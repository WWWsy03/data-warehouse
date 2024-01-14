import { PageContainer, ProForm, ProFormInstance } from '@ant-design/pro-components'
import { Alert, Card, Empty, List, Table } from 'antd'
import { useRef, useState } from 'react'
import QueryResult from '@/components/QueryResult/QueryResult'
import QueryLog from '@/components/QueryResult/QueryLog'
import {
  queryCooperateByActorActor,
  queryCooperateByDirectorActor,
  queryMovieByPositiveReview,
} from '@/services/data-warehouse/api'

type QueryParam = {}

export default function ByCategory() {
  const formRef = useRef<ProFormInstance<QueryParam>>()

  const [queryResult, setQueryResult] = useState<API.QueryResult<API.AACooperate[]> | null>(null)

  const query = async () => {
    const resp = await queryCooperateByActorActor()
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
              <div className={'my-3'}>本次查询无需输入参数</div>
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
                    { title: '合作次数', dataIndex: 'collaborationNumber' },
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
