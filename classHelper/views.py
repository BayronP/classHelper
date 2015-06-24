from django.shortcuts import render
from django.shortcuts import render_to_response
from classHelper.models import ClassUser, ProblemSet, Problem, Solution
from classHelper.serializers import ClassUserSerializer, ProblemSetSerializer, ProblemSerializer, SolutionSerializer
from classHelper.permissions import IsTeacherOrAdmin, IsAdminOrNot, IsTeacherOrAdmin1
from rest_framework import viewsets
from rest_framework import permissions, status
from rest_framework.decorators import api_view, detail_route
from rest_framework.response import Response
from rest_framework.reverse import reverse
from django.http import request
from django.http import HttpResponse


# Create your views here.
class ClassUserViewSet(viewsets.ModelViewSet):
    queryset = ClassUser.objects.all()
    serializer_class = ClassUserSerializer
    permission_classes = (permissions.IsAuthenticated,
                          IsAdminOrNot,)

class ProblemSetViewSet(viewsets.ModelViewSet):
    queryset = ProblemSet.objects.all()
    serializer_class = ProblemSetSerializer
    permission_classes = (permissions.IsAuthenticated,
                          IsTeacherOrAdmin, )
    
    def perform_create(self, serializer):
        obj = ClassUser.objects.get(user = self.request.user)
        serializer.save(creater = obj)

class ProblemViewSet(viewsets.ModelViewSet):
    queryset = Problem.objects.all()
    serializer_class = ProblemSerializer
    permission_classes = (permissions.IsAuthenticated,
                          IsTeacherOrAdmin1, )
    
    def perform_create(self, serializer):
        obj = ProblemSet.objects.get(problemSetCode = self.request.data['code'])
        serializer.save(problemSet = obj)

class SolutionViewSet(viewsets.ModelViewSet):
    queryset = Solution.objects.all()
    serializer_class = SolutionSerializer
    permission_classes = (permissions.IsAuthenticated,)
    
    def perform_create(self, serializer):
        obj = ProblemSet.objects.get(problemSetCode = self.request.data['code'])
        user = ClassUser.objects.get(user = self.request.user)
        serializer.save(solver = user, problems = obj)


def test(request):
    # @print(request.user.username)
    return render(request,"index.html")

def login(request):
    return render(request,"login1.html")

def chart(request):
    return render(request,"charts.html")

def my_test(request):
    return render(request,"1.html")

def add_problem(request):
    return render(request, "add.html")

def add_a_problem(request):
    return render(request,"add_pro.html")