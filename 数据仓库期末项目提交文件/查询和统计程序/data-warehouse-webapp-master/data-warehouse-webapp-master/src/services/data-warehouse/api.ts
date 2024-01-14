import { request } from '@umijs/max'

// 按时间 - 年份查询
export async function queryByTimeYear(year: number) {
  return request<API.QueryResult<number>>('/api/time/year', {
    method: 'GET',
    params: {
      year: year,
    },
  })
}

export async function queryByTimeYearMonth(year: number, month: number) {
  return request<API.QueryResult<number>>('/api/time/year-month', {
    method: 'GET',
    params: {
      year: year,
      month: month,
    },
  })
}

export async function queryByTimeYearMonthDay(year: number, month: number, day: number) {
  return request<API.QueryResult<number>>('/api/time/year-month-day', {
    method: 'GET',
    params: {
      year: year,
      month: month,
      day: day,
    },
  })
}

export async function queryByTimeYearSeason(year: number, season: string) {
  return request<API.QueryResult<number>>('/api/time/year-season', {
    method: 'GET',
    params: {
      year: year,
      season: season,
    },
  })
}

export async function queryByMovieName(name: string) {
  return request<API.QueryResult<API.Movie[]>>('/api/movie', {
    method: 'GET',
    params: {
      movieName: name,
    },
  })
}

export async function queryByDirectorName(name: string) {
  return request<API.QueryResult<string[]>>('/api/director', {
    method: 'GET',
    params: {
      directorName: name,
    },
  })
}

export async function queryByActorName(name: string) {
  return request<API.QueryResult<string[]>>('/api/actor', {
    method: 'GET',
    params: {
      actorName: name,
    },
  })
}

export async function queryActorByDirector(name: string) {
  return request<API.QueryResult<string[]>>('/api/director-actor/actor-by-director', {
    method: 'GET',
    params: {
      directorName: name,
    },
  })
}

export async function queryDirectorByDirector(name: string) {
  return request<API.QueryResult<string[]>>('/api/director-actor/director-by-director', {
    method: 'GET',
    params: {
      directorName: name,
    },
  })
}

export async function queryDirectorByActor(name: string) {
  return request<API.QueryResult<string[]>>('/api/director-actor/director-by-actor', {
    method: 'GET',
    params: {
      actorName: name,
    },
  })
}

export async function queryActorByActor(name: string) {
  return request<API.QueryResult<string[]>>('/api/director-actor/actor-by-actor', {
    method: 'GET',
    params: {
      actorName: name,
    },
  })
}

export async function queryMovieByCategory(category: string) {
  return request<API.QueryResult<string[]>>('/api/category/get-movies', {
    method: 'GET',
    params: {
      type: category,
    },
  })
}

export async function queryMovieByBetterGrade(grade: number) {
  return request<API.QueryResult<string[]>>('/api/review/better-than', {
    method: 'GET',
    params: {
      grade: grade,
    },
  })
}

export async function queryMovieByPositiveReview() {
  return request<API.QueryResult<string[]>>('/api/review/positive', {
    method: 'GET',
    params: {},
  })
}

export async function queryCooperateByDirectorActor() {
  return request<API.QueryResult<API.DACooperate[]>>('/api/relation/director-actor', {
    method: 'GET',
    params: {},
  })
}

export async function queryCooperateByDirectorDirector() {
  return request<API.QueryResult<API.DDCooperate[]>>('/api/relation/director-director', {
    method: 'GET',
    params: {},
  })
}

export async function queryCooperateByActorActor() {
  return request<API.QueryResult<API.AACooperate[]>>('/api/relation/actor-actor', {
    method: 'GET',
    params: {},
  })
}

export async function queryByHotActorActor(category: string) {
  return request<API.QueryResult<API.HotAA[]>>('/api/relation/hot-actor-actor', {
    method: 'GET',
    params: {
      type: category,
    },
  })
}

export async function queryByYearAndType(year: number, category: string) {
  return request<API.QueryResult<number>>('/api/compose/year-type', {
    method: 'GET',
    params: {
      year: year,
      type: category,
    },
  })
}

export async function queryByYearAndDirector(year: number, director: string) {
  return request<API.QueryResult<number>>('/api/compose/year-director', {
    method: 'GET',
    params: {
      year: year,
      director_name: director,
    },
  })
}

export async function queryNonMovie() {
  return request<API.QueryResult<API.NonMovie>>('/api/trace/non-movie', {
    method: 'GET',
    params: {},
  })
}

export async function queryHarryPotter() {
  return request<API.QueryResult<API.HarryPotter>>('/api/trace/harry-potter', {
    method: 'GET',
    params: {},
  })
}
