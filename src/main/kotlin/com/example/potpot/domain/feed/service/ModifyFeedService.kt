package com.example.potpot.domain.feed.service

import com.example.potpot.domain.feed.domain.FeedRepository
import com.example.potpot.domain.feed.presentation.dto.request.FeedRequest
import com.example.potpot.domain.user.exception.UserMismatchException
import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ModifyFeedService(
    private val feedRepository: FeedRepository,
    private val userFacade: UserFacade
) {

    @Transactional
    fun execute(id: Long, request: FeedRequest) {
        val user = userFacade.getCurrentUser()
        val feed = feedRepository.findById(id).get()

        if (user.id != feed.user.id) throw UserMismatchException
        feed.modifyFeed(request.title, request.content, request.date, request.category)
    }
}
