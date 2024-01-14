import { PageContainer, ProForm, ProFormDigit, ProFormInstance, ProFormText } from '@ant-design/pro-components'
import { Alert, Card, Empty, List, message } from 'antd'
import { useRef, useState } from 'react'
import QueryResult from '@/components/QueryResult/QueryResult'
import QueryLog from '@/components/QueryResult/QueryLog'
import { queryByDirectorName, queryMovieByBetterGrade, queryMovieByCategory } from '@/services/data-warehouse/api'

type QueryParam = { grade: number }

export default function ByCategory() {
  const formRef = useRef<ProFormInstance<QueryParam>>()

  const [queryResult, setQueryResult] = useState<API.QueryResult<string[]> | null>(null)

  const query = async () => {
    const data = await formRef.current?.validateFields()
    if (data === undefined || data?.grade === undefined) {
      message.warning('有必填字段未填')
      return
    }
    const resp = await queryMovieByBetterGrade(data.grade)
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
              <ProFormDigit required name="grade" label="评分不低于" />
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
