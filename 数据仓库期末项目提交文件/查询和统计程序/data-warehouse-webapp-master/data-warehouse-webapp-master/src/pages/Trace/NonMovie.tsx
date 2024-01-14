import { PageContainer, ProForm, ProFormInstance } from '@ant-design/pro-components'
import { Alert, Card, Empty, List, Table } from 'antd'
import { useRef, useState } from 'react'
import QueryResult from '@/components/QueryResult/QueryResult'
import QueryLog from '@/components/QueryResult/QueryLog'
import { queryCooperateByDirectorActor, queryMovieByPositiveReview, queryNonMovie } from '@/services/data-warehouse/api'

type QueryParam = {}

export default function NonMovie() {
  const formRef = useRef<ProFormInstance<QueryParam>>()

  const [queryResult, setQueryResult] = useState<API.QueryResult<API.NonMovie> | null>(null)

  const query = async () => {
    const resp = await queryNonMovie()
    if (resp.success) {
      setQueryResult(resp)
    }
  }

  return (
    <PageContainer>
      <div className={'flex flex-col gap-5'}>
        <Card>在过程中，我们找出了多少非电影的数据？</Card>
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
              <div className={'flex flex-col gap-2 h-full items-center justify-center'}>
                <div className={'text-5xl font-bold'}>{queryResult.data.nonMovieDataCount}</div>
                共有{queryResult.data.nonMovieDataCount}部非电影的数据
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
