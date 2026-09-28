variable "floci_endpoint" {
  description = "Floci endpoint"
  type        = string
  default     = "http://localhost:4566"
}

variable "db_name" {
  description = "HealthSphere database name"
  type        = string
  default     = "healthsphere"
}

variable "db_username" {
  description = "HealthSphere database username"
  type        = string
  default     = "healthsphere"
}

variable "db_password" {
  description = "HealthSphere database password"
  type        = string
  sensitive   = true
  default     = "HealthSphereDev@123"
}

