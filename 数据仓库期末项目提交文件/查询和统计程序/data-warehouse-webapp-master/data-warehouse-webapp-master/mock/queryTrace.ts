import { Request, Response } from 'express'

const waitTime = (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true)
    }, time)
  })
}

const getNonMovie = async (req: Request, res: Response) => {
  await waitTime(1000)

  const year = req.query['year']

  res.json({
    data: { nonMovieDataCount: Math.floor(Math.random() * 20000) },
    id: '1',
    success: true,
    modelName: ['关系型-mysql', '关系型-hive', '图数据库'],
    modelTime: [0, 0, 0],
    modelLog: ['无', '无', '无'],
  } satisfies API.QueryResult<API.NonMovie>)
}

const getHarryPotter = async (req: Request, res: Response) => {
  await waitTime(1000)

  const year = req.query['year']

  res.json({
    data: {
      formatCount: Math.floor(Math.random() * 2000),
      harryPotterMovieCount: Math.floor(Math.random() * 2000),
      mergedWebPageCount: Math.floor(Math.random() * 2000),
      webPageCount: Math.floor(Math.random() * 2000),
    },
    id: '1',
    success: true,
    modelName: ['关系型-mysql', '关系型-hive', '图数据库'],
    modelTime: [0, 0, 0],
    modelLog: ['无', '无', '无'],
  } satisfies API.QueryResult<API.HarryPotter>)
}

export default {
  'GET /api/trace/non-movie': getNonMovie,
  'GET /api/trace/harry-potter': getHarryPotter,
}
