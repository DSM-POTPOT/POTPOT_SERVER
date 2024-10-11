package com.example.potpot_server.domain.user.service

import com.example.potpot_server.domain.user.presentation.dto.response.ProfileImageUploadResponse
import com.example.potpot_server.global.utils.S3Utils
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ProfileImageUploadService(
    private val s3Utils: S3Utils
) {
    fun execute(multipartFile: MultipartFile): ProfileImageUploadResponse =
        ProfileImageUploadResponse(s3Utils.saveFile(multipartFile))
}
