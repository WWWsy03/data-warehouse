import { PageContainer, ProForm, ProFormDigit, ProFormInstance } from '@ant-design/pro-components'
import { Alert, Card, Empty, message } from 'antd'
import { useRef, useState } from 'react'
import QueryResult from '@/components/QueryResult/QueryResult'
import QueryLog from '@/components/QueryResult/QueryLog'
import { queryByTimeYearMonthDay } from '@/services/data-warehouse/api'

type QueryParam = { year?: number; month?: number; day?: number }

export default function ByTimeYearMonthDay() {
  const formRef = useRef<ProFormInstance<QueryParam>>()

  const [year, setYear] = useState(0)
  const [month, setMonth] = useState(0)
  const [day, setDay] = useState(0)
  const [queryResult, setQueryResult] = useState<API.QueryResult<number> | null>(null)

  const query = async () => {
    const data = await formRef.current?.validateFields()
    if (data === undefined || data?.year === undefined || data?.month === undefined || data?.day === undefined) {
      message.warning('有必填字段未填')
      return
    }
    const resp = await queryByTimeYearMonthDay(data.year, data.month, data.day)
    if (resp.success) {
      setYear(data.year)
      setMonth(data.month)
      setDay(data.day)
      setQueryResult(resp)
    }
  }

  return (
    <PageContainer>
      <div className={'flex flex-col gap-5'}>
        <Card>
          <ProForm<QueryParam> formRef={formRef} onFinish={query} autoFocusFirstInput>
            <ProForm.Group title={'查询参数'}>
              <ProFormDigit required name="year" label="年份" />
              <ProFormDigit required name="month" label="月份" />
              <ProFormDigit required name="day" label="日期" />
            </ProForm.Group>
          </ProForm>
        </Card>

        {(queryResult !== null && (
          <>
            <Alert message="查询成功" type="success" showIcon />

            <QueryResult result={queryResult}>
              <div className={'flex flex-col gap-2 h-full items-center justify-center'}>
                <div className={'text-5xl font-bold'}>{queryResult.data}</div>
                共查询到{queryResult.data}部在{year}年{month}月{day}日的影片
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
