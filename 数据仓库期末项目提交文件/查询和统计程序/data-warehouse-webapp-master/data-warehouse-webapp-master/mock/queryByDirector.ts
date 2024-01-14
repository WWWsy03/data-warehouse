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

  const name = req.query['director_name']

  res.json({
    data: ['Inception', 'Interstellar', 'The Dark Knight'],
    id: '1',
    success: true,
    modelName: ['关系型-mysql', '关系型-hive', '图数据库'],
    modelTime: [Math.floor(Math.random() * 200), Math.floor(Math.random() * 2000), Math.floor(Math.random() * 300)],
    modelLog: [
      `SELECT * FROM movies WHERE director='${name}'`,
      `SELECT * FROM movies WHERE director='${name}'`,
      '这个我不知道怎么写',
    ],
  } satisfies API.QueryResult<string[]>)
}

export default {
  'GET /api/director': getMovie,
}
