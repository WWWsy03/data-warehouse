import { Request, Response } from 'express'
import { queryActorByActor, queryDirectorByActor, queryDirectorByDirector } from '@/services/data-warehouse/api'

const waitTime = (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true)
    }, time)
  })
}

const queryActor = async (req: Request, res: Response) => {
  await waitTime(1000)

  res.json({
    data: ['演员1', '演员2', '演员3'],
    id: '1',
    success: true,
    modelName: ['关系型-mysql', '关系型-hive', '图数据库'],
    modelTime: [Math.floor(Math.random() * 200), Math.floor(Math.random() * 2000), Math.floor(Math.random() * 300)],
    modelLog: [`略`, `略`, '略'],
  } satisfies API.QueryResult<string[]>)
}

const queryDirector = async (req: Request, res: Response) => {
  await waitTime(1000)

  res.json({
    data: ['导演1', '导演2', '导演3'],
    id: '1',
    success: true,
    modelName: ['关系型-mysql', '关系型-hive', '图数据库'],
    modelTime: [Math.floor(Math.random() * 200), Math.floor(Math.random() * 2000), Math.floor(Math.random() * 300)],
    modelLog: [`略`, `略`, '略'],
  } satisfies API.QueryResult<string[]>)
}

export default {
  'GET /api/director-actor/actor-by-director': queryActor,
  'GET /api/director-actor/director-by-director': queryDirector,
  'GET /api/director-actor/director-by-actor': queryDirector,
  'GET /api/director-actor/actor-by-actor': queryActor,
}
