import {Card} from "antd";
import {Column, ColumnConfig} from '@ant-design/plots';
import React from "react";

const PerformanceFigure = ({result}: { result: API.QueryResult<any> }) => {
  const data = result.modelName.map((v, i) => ({type: v, time: result.modelTime[i]}))
  const config: ColumnConfig = {
    data,
    xField: "type",
    yField: "time",
    height: 350
  }
  return (
    <Column {...config}></Column>
  )
}

export default function QueryResult({result, children}: { result: API.QueryResult<any>, children: React.ReactElement }) {
  return (
    <Card>
      <div className={"min-h-[300px]"}>
        <div className={"grid grid-cols-12 gap-2"}>
          <div className={"col-span-5"}>
            <div className={"font-bold"}>性能对比</div>
            <div className={"p-3"}>
              <PerformanceFigure result={result}/>
            </div>
          </div>
          <div className={"col-span-7"}>
            <div className={"font-bold"}>查询结果</div>
            {children}
          </div>
        </div>
      </div>
    </Card>
  )
}
