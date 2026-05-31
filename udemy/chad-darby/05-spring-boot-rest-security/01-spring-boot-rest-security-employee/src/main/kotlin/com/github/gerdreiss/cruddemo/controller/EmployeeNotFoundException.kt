package com.github.gerdreiss.cruddemo.controller

data class EmployeeNotFoundException(override val message: String) : RuntimeException(message)
