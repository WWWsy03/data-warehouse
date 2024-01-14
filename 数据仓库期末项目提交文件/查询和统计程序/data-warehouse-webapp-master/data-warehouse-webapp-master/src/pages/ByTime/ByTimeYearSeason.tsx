import { PageContainer, ProForm, ProFormDigit, ProFormInstance, ProFormText } from '@ant-design/pro-components'
import { Alert, Card, Empty, message } from 'antd'
import { useRef, useState } from 'react'
import QueryResult from '@/components/QueryResult/QueryResult'
import QueryLog from '@/components/QueryResult/QueryLog'
import { queryByTimeYear, queryByTimeYearSeason } from '@/services/data-warehouse/api'

export default function ByTimeQuery() {
  const formRef = useRef<
    ProFormInstance<{
      year: number
      season: string
    }>
  >()

  const [year, setYear] = useState(0)
  const [season, setSeason] = useState('')
  const [queryResult, setQueryResult] = useState<API.QueryResult<number> | null>(null)

  const query = async () => {
    const data = await formRef.current?.validateFields()
    if (data === undefined || data?.year === undefined) {
      message.warning('有必填字段未填')
      return
    }
    const resp = await queryByTimeYearSeason(data.year, data.season)
    if (resp.success) {
      setYear(data.year)
      setSeason(data.season)
      setQueryResult(resp)
    }
  }

  return (
    <PageContainer>
      <div className={'flex flex-col gap-5'}>
        <Card>
          <ProForm<{ year?: number; season: string }> formRef={formRef} onFinish={query} autoFocusFirstInput>
            <ProForm.Group title={'查询参数'}>
              <ProFormDigit required name="year" label="年份" />
              <ProFormText required name="season" label="季度" placeholder="如Q1" />
            </ProForm.Group>
          </ProForm>
        </Card>

        {(queryResult !== null && (
          <>
            <Alert message="查询成功" type="success" showIcon />

            <QueryResult result={queryResult}>
              <div className={'flex flex-col gap-2 h-full items-center justify-center'}>
                <div className={'text-5xl font-bold'}>{queryResult.data}</div>
                共查询到{queryResult.data}部在{year}年{season}的影片
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
