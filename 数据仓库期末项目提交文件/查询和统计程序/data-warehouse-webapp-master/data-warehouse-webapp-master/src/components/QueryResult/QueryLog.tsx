import {ProCard} from "@ant-design/pro-components"

export default function QueryLog({result}: { result: API.QueryResult<any> }) {
  return (
    <ProCard direction="row" title={"查询日志"}>
      {result.modelName.map((_, i) => (
        <ProCard key={i}>
          <div className={"text-center font-bold"}>{result.modelName[i]}</div>
          <div className={"p-3 mt-2 bg-gray-200 rounded-xl"}>{result.modelLog[i]}</div>
        </ProCard>
      ))}
    </ProCard>
  )
}
