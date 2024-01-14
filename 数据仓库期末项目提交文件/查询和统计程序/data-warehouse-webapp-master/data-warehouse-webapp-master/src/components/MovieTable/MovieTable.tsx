import {ProColumns, ProTable} from "@ant-design/pro-components";

const columns: ProColumns<API.Movie, "text">[] = [
  {
    title: "产品ID",
    dataIndex: 'productId',
    tooltip: "Amazon中的商品ID",
  },
  {
    title: "评论数量",
    dataIndex: "commentCounts",
  },
  {
    title: "价格",
    dataIndex: "cost",
  },
  {
    title: "格式",
    dataIndex: "format",
  },
  {
    title: "评分",
    dataIndex: "grade",
  }
]

export default function MovieTable({title, movies}: { title: string, movies: API.Movie[] }) {
  return (
    <ProTable<API.Movie>
      headerTitle={title}
      rowKey="key"
      columns={columns}
      dataSource={movies}
    />
  )
}
