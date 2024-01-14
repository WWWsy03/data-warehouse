import { Request, Response } from 'express'

const waitTime = (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true)
    }, time)
  })
}

const getMovie = async (req: Request, res: Response) => {
  await waitTime(1000)

  const name = req.query['name']

  res.json({
    data: [
      {
        productId: 'B002ACOT5M',
        commentCounts: '1',
        cost: '$29.95',
        format: 'Blu-ray',
        grade: '4.6',
      },
      {
        productId: 'B002TTOT5M',
        commentCounts: '1',
        cost: '$29.95',
        format: 'DVD',
        grade: '4.6',
      },
      {
        productId: 'B002AXCDEF',
        commentCounts: '2',
        cost: '$29.95',
        format: 'VHS Tape',
        grade: '4.2',
      },
    ],
    id: '1',
    success: true,
    modelName: ['关系型-mysql', '关系型-hive', '图数据库'],
    modelTime: [Math.floor(Math.random() * 200), Math.floor(Math.random() * 2000), Math.floor(Math.random() * 300)],
    modelLog: [`SELECT * FROM movies WHERE name='${name}'`, `SELECT * FROM movies WHERE name='${name}'`, '这个我不知道怎么写'],
  } satisfies API.QueryResult<API.Movie[]>)
}

export default {
  'GET /api/movie': getMovie,
}
