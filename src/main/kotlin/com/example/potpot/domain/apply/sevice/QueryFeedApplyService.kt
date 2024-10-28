package com.example.potpot.domain.apply.sevice

import com.example.potpot.domain.apply.domain.ApplyRepository
import com.example.potpot.domain.apply.presentation.dto.response.QueryByFeedResponse
import com.example.potpot.domain.feed.domain.FeedRepository
import com.example.potpot.domain.feed.exception.FeedNotFoundException
import com.example.potpot.domain.user.exception.UserMismatchException
import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryFeedApplyService(
    private val applyRepository: ApplyRepository,
    private val feedRepository: FeedRepository,
    private val userFacade: UserFacade
) {

    @Transactional(readOnly = true)
    fun execute(feedId: Long): List<QueryByFeedResponse> {
        val user = userFacade.getCurrentUser()
        val feed = feedRepository.findByIdOrNull(feedId) ?: throw FeedNotFoundException
        val applies = applyRepository.findByFeed(feed)

        if (user.schoolNumber != feed.user.schoolNumber) throw UserMismatchException

        return applies.map {
            QueryByFeedResponse(
                id = it.id,
                userName = it.user.name,
                isOK = it.isOK
            )
        }
    }
}
