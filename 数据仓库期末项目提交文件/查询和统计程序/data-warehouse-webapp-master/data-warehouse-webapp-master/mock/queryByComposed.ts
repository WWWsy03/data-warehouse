import { Request, Response } from 'express'

const waitTime = (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true)
    }, time)
  })
}

const getCount = async (req: Request, res: Response) => {
  await waitTime(1000)

  const year = req.query['year']

  res.json({
    data: Math.floor(Math.random() * 2000),
    id: '1',
    success: true,
    modelName: ['关系型-mysql', '关系型-hive', '图数据库'],
    modelTime: [Math.floor(Math.random() * 200), Math.floor(Math.random() * 2000), Math.floor(Math.random() * 300)],
    modelLog: [
      `SELECT count(*) FROM movies WHERE year=${year}`,
      `SELECT count(*) FROM movies WHERE year=${year}`,
      '这个我不知道怎么写',
    ],
  } satisfies API.QueryResult<number>)
}

export default {
  'GET /api/compose/year-type': getCount,
  'GET /api/compose/year-director': getCount,
}
