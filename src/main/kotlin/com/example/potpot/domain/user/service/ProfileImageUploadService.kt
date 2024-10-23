package com.example.potpot.domain.user.service

import com.example.potpot.domain.user.presentation.dto.response.ProfileImageUploadResponse
import com.example.potpot.global.utils.S3Utils
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ProfileImageUploadService(
    private val s3Utils: S3Utils
) {
    fun execute(multipartFile: MultipartFile): ProfileImageUploadResponse =
        ProfileImageUploadResponse(s3Utils.saveFile(multipartFile))
}
