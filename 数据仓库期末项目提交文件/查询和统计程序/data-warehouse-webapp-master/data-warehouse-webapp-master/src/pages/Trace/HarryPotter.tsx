import { PageContainer, ProForm, ProFormInstance } from '@ant-design/pro-components'
import { Alert, Card, Col, Empty, List, Row, Statistic, Table } from 'antd'
import { useRef, useState } from 'react'
import QueryResult from '@/components/QueryResult/QueryResult'
import QueryLog from '@/components/QueryResult/QueryLog'
import {
  queryCooperateByDirectorActor,
  queryHarryPotter,
  queryMovieByPositiveReview,
  queryNonMovie,
} from '@/services/data-warehouse/api'

type QueryParam = {}

export default function NonMovie() {
  const formRef = useRef<ProFormInstance<QueryParam>>()

  const [queryResult, setQueryResult] = useState<API.QueryResult<API.HarryPotter> | null>(null)

  const query = async () => {
    const resp = await queryHarryPotter()
    if (resp.success) {
      setQueryResult(resp)
    }
  }

  return (
    <PageContainer>
      <div className={'flex flex-col gap-5'}>
        <Card>
          在ETL和数据预处理中，我们找到多少哈利波特系列的电影？这个电影有多少版本？有多少网页？哈利波特第一部我们合并了多少个不同的网页？
        </Card>
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
                <Row>
                  <Col span={12}>
                    <Statistic title="系列电影数量" value={queryResult.data.harryPotterMovieCount} />
                  </Col>
                  <Col span={12}>
                    <Statistic title="电影版本数量" value={queryResult.data.formatCount} />
                  </Col>
                </Row>
                <Row>
                  <Col span={12}>
                    <Statistic title="网页数量" value={queryResult.data.webPageCount} />
                  </Col>
                  <Col span={12}>
                    <Statistic title="合并网页数量" value={queryResult.data.mergedWebPageCount} />
                  </Col>
                </Row>
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
