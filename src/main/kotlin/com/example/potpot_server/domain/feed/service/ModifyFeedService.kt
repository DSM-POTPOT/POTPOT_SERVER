package com.example.potpot_server.domain.feed.service

import com.example.potpot_server.domain.feed.domain.FeedRepository
import com.example.potpot_server.domain.feed.exception.FeedNotFoundException
import com.example.potpot_server.domain.feed.presentation.dto.request.FeedRequest
import com.example.potpot_server.domain.user.exception.UserMismatchException
import com.example.potpot_server.domain.user.facade.UserFacade
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
        val feed = feedRepository.findById(id).orElseThrow() ?: throw FeedNotFoundException

        if (user != feed.user) throw UserMismatchException

        feedRepository.save(
            feed.copy(
                title = request.title,
                content = request.content,
                date = request.date,
                category = request.category
            )
        )
    }
}
