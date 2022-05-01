package io.github.v1servicenotification.domain.setting.presentation

import io.github.v1servicenotification.domain.setting.service.ActivateNotificationCategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/tags")
@RestController
class SettingController(
    private val activateNotificationCategoryService: ActivateNotificationCategoryService,
    private val deActivateNotificationCategoryService: ActivateNotificationCategoryService
) {

    @PatchMapping("/{category-uuid}")
    fun activateNotificationCategory(@PathVariable("category-uuid") uuid: UUID): ResponseEntity<Unit> {
        return ResponseEntity(
            HttpStatus.valueOf(
                activateNotificationCategoryService.execute(uuid)
            )
        )
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{category-uuid}")
    fun deActivateNotificationCategory(@PathVariable("category-uuid") uuid: UUID) {
        deActivateNotificationCategoryService.execute(uuid)
    }

}