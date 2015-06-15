from rest_framework import permissions
from classHelper.models import ClassUser
from django.contrib.auth.models import User

class IsTeacherOrAdmin(permissions.BasePermission):
    def has_object_permission(self, request, view, obj):
        if request.method in permissions.SAFE_METHODS:
            return True
        user = ClassUser.objects.get(user = User.objects.get(username = request.user.username))
        if user.role == 's':
            return False
        return obj.creater == request.user

    def has_permission(self, request, view):
        if request.method in permissions.SAFE_METHODS:
            return True
        user = ClassUser.objects.get(user = User.objects.get(username = request.user.username))
        if user.role == 's':
            return False
        return True


class IsTeacherOrAdmin1(permissions.BasePermission):
    def has_object_permission(self, request, view, obj):
        if request.method in permissions.SAFE_METHODS:
            return True
        user = ClassUser.objects.get(user = User.objects.get(username = request.user.username))
        if user.role == 's':
            return False
        return obj.problemSet.creater == request.user

    def has_permission(self, request, view):
        if request.method in permissions.SAFE_METHODS:
            return True
        user = ClassUser.objects.get(user = User.objects.get(username = request.user.username))
        if user.role == 's':
            return False
        return True
                        

class IsAdminOrNot(permissions.BasePermission):
    def has_object_permission(self, request, view, obj):
        user = ClassUser.objects.get(user = User.objects.get(username = request.user.username))
        return obj.user == request.user

    def has_permission(self, request, view):
        if request.method not in permissions.SAFE_METHODS:
            return True
        # if role is teacher, can got student list
        user = ClassUser.objects.get(user = User.objects.get(username = request.user.username))
        return user.role == 'a'
      
