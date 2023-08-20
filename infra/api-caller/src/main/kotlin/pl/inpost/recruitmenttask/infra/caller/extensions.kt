package pl.inpost.recruitmenttask.infra.caller

fun <T> T.toSuccessResultDomain(): ResultDomain<T, Nothing> = ResultDomain.Success(this)

fun <T> T.toErrorResultDomain(): ResultDomain<Nothing, T> = ResultDomain.Error(this)
