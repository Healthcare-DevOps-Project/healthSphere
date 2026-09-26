output "ec2_instance_id" {
  value = aws_instance.web.id
}

output "ec2_private_ip" {
  value = aws_instance.web.private_ip
}

output "ec2_public_ip" {
  value = aws_instance.web.public_ip
}

output "private_ec2_instance_id" {
  value = aws_instance.private.id
}

output "private_ec2_ip" {
  value = aws_instance.private.private_ip
}