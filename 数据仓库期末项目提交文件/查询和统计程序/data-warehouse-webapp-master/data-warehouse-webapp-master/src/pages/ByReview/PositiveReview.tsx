import { PageContainer, ProForm, ProFormDigit, ProFormInstance, ProFormText } from '@ant-design/pro-components'
import { Alert, Card, Empty, List, message } from 'antd'
import { useRef, useState } from 'react'
import QueryResult from '@/components/QueryResult/QueryResult'
import QueryLog from '@/components/QueryResult/QueryLog'
import {
  queryByDirectorName,
  queryMovieByBetterGrade,
  queryMovieByCategory,
  queryMovieByPositiveReview,
} from '@/services/data-warehouse/api'

type QueryParam = {}

export default function ByCategory() {
  const formRef = useRef<ProFormInstance<QueryParam>>()

  const [queryResult, setQueryResult] = useState<API.QueryResult<string[]> | null>(null)

  const query = async () => {
    const resp = await queryMovieByPositiveReview()
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
                <List bordered dataSource={queryResult.data} renderItem={(item) => <List.Item>{item}</List.Item>} />
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
