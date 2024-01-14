import { Request, Response } from 'express'

const waitTime = (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true)
    }, time)
  })
}

const getDA = async (req: Request, res: Response) => {
  await waitTime(1000)

  const name = req.query['actor_name']

  res.json({
    data: [
      { actorName: '演员1', collaborationNumber: 50, directorName: '导演1' },
      { actorName: '演员2', collaborationNumber: 25, directorName: '导演2' },
      { actorName: '演员3', collaborationNumber: 10, directorName: '导演3' },
    ],
    id: '1',
    success: true,
    modelName: ['关系型-mysql', '关系型-hive', '图数据库'],
    modelTime: [Math.floor(Math.random() * 200), Math.floor(Math.random() * 2000), Math.floor(Math.random() * 300)],
    modelLog: [`SELECT * FROM movies WHERE actor='${name}'`, `SELECT * FROM movies WHERE actor='${name}'`, '这个我不知道怎么写'],
  } satisfies API.QueryResult<API.DACooperate[]>)
}

const getAA = async (req: Request, res: Response) => {
  await waitTime(1000)

  const name = req.query['actor_name']

  res.json({
    data: [
      { actor1: '演员1', collaborationNumber: 50, actor2: '演员4' },
      { actor1: '演员2', collaborationNumber: 25, actor2: '演员5' },
      { actor1: '演员3', collaborationNumber: 10, actor2: '演员6' },
    ],
    id: '1',
    success: true,
    modelName: ['关系型-mysql', '关系型-hive', '图数据库'],
    modelTime: [Math.floor(Math.random() * 200), Math.floor(Math.random() * 2000), Math.floor(Math.random() * 300)],
    modelLog: [`SELECT * FROM movies WHERE actor='${name}'`, `SELECT * FROM movies WHERE actor='${name}'`, '这个我不知道怎么写'],
  } satisfies API.QueryResult<API.AACooperate[]>)
}

const getDD = async (req: Request, res: Response) => {
  await waitTime(1000)

  const name = req.query['actor_name']

  res.json({
    data: [
      { director1: '导演4', collaborationNumber: 50, director2: '导演1' },
      { director1: '导演5', collaborationNumber: 25, director2: '导演2' },
      { director1: '导演6', collaborationNumber: 10, director2: '导演3' },
    ],
    id: '1',
    success: true,
    modelName: ['关系型-mysql', '关系型-hive', '图数据库'],
    modelTime: [Math.floor(Math.random() * 200), Math.floor(Math.random() * 2000), Math.floor(Math.random() * 300)],
    modelLog: [`SELECT * FROM movies WHERE actor='${name}'`, `SELECT * FROM movies WHERE actor='${name}'`, '这个我不知道怎么写'],
  } satisfies API.QueryResult<API.DDCooperate[]>)
}

const getHotAA = async (req: Request, res: Response) => {
  await waitTime(1000)

  const name = req.query['actor_name']

  res.json({
    data: [
      { actor1: '演员1', actor2: '演员4' },
      { actor1: '演员2', actor2: '演员5' },
      { actor1: '演员3', actor2: '演员6' },
    ],
    id: '1',
    success: true,
    modelName: ['关系型-mysql', '关系型-hive', '图数据库'],
    modelTime: [Math.floor(Math.random() * 200), Math.floor(Math.random() * 2000), Math.floor(Math.random() * 300)],
    modelLog: [`SELECT * FROM movies WHERE actor='${name}'`, `SELECT * FROM movies WHERE actor='${name}'`, '这个我不知道怎么写'],
  } satisfies API.QueryResult<API.HotAA[]>)
}

export default {
  'GET /api/relation/director-actor': getDA,
  'GET /api/relation/director-director': getDD,
  'GET /api/relation/actor-actor': getAA,
  'GET /api/relation/hot-actor-actor': getHotAA,
}
