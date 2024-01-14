declare namespace API {
  type Movie = {
    productId: string
    commentCounts: string
    cost: string
    format: string
    grade: string
  }

  type QueryResult<T> = {
    id: string
    success: boolean
    data: T
    modelName: string[]
    modelTime: number[]
    modelLog: string[]
  }

  type DACooperate = {
    directorName: string
    actorName: string
    collaborationNumber: number
  }

  type DDCooperate = {
    director1: string
    director2: string
    collaborationNumber: number
  }

  type AACooperate = {
    actor1: string
    actor2: string
    collaborationNumber: number
  }

  type HotAA = {
    actor1: string
    actor2: string
  }

  type NonMovie = {
    nonMovieDataCount: number
  }

  type HarryPotter = {
    harryPotterMovieCount: number
    formatCount: number
    webPageCount: number
    mergedWebPageCount: number
  }
}
