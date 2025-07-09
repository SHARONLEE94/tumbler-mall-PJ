/**
 * Provides centralized exception handling for both MVC (view-based) and REST (API) layers of the application.
 *
 * <p>This package contains:
 * <ul>
 *   <li>{@link GlobalViewExceptionHandler} — handles exceptions thrown by Spring MVC controllers,
 *       mapping them to user-friendly error views.</li>
 *   <li>{@link GlobalApiExceptionHandler} — handles exceptions thrown by REST controllers,
 *       mapping them to standardized JSON responses wrapped in {@link ApiResponse}.</li>
 *   <li>{@link ApiResponse} — a generic wrapper for all API responses, encapsulating
 *       status codes, messages, and payload data.</li>
 *   <li>Optional enums or classes for error codes and metadata to ensure consistency
 *       across the application’s error handling strategy.</li>
 * </ul>
 *
 * <p>By centralizing exception logic here, controllers remain focused on business logic,
 * and error responses—whether HTML views or JSON payloads—are consistent, maintainable,
 * and easy to extend.
 *
 * @author whosoonhwang
 */
package com.tumblermall.global.exception;