# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
from django.conf import settings


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.CreateModel(
            name='ClassUser',
            fields=[
                ('id', models.AutoField(verbose_name='ID', auto_created=True, serialize=False, primary_key=True)),
                ('created', models.DateTimeField(auto_now_add=True)),
                ('role', models.CharField(default='student', choices=[('t', 'teacher'), ('s', 'student'), ('a', 'admin')], max_length=100)),
                ('user', models.OneToOneField(to=settings.AUTH_USER_MODEL)),
            ],
            options={
                'ordering': ('created',),
            },
        ),
        migrations.CreateModel(
            name='Problem',
            fields=[
                ('id', models.AutoField(verbose_name='ID', auto_created=True, serialize=False, primary_key=True)),
                ('created', models.DateTimeField(auto_now_add=True)),
                ('problemDesc', models.TextField()),
                ('problemSelect', models.TextField()),
            ],
        ),
        migrations.CreateModel(
            name='ProblemSet',
            fields=[
                ('problemSetCode', models.CharField(serialize=False, primary_key=True, max_length=6)),
                ('problemSetDesc', models.CharField(max_length=100)),
                ('problemsAns', models.CharField(max_length=100)),
                ('creater', models.ForeignKey(related_name='problemSets', to='classHelper.ClassUser')),
            ],
        ),
        migrations.CreateModel(
            name='Solution',
            fields=[
                ('id', models.AutoField(verbose_name='ID', auto_created=True, serialize=False, primary_key=True)),
                ('ans', models.CharField(max_length=100)),
                ('problems', models.ForeignKey(related_name='solutions', to='classHelper.ProblemSet')),
                ('solver', models.ForeignKey(related_name='solutions', to='classHelper.ClassUser')),
            ],
        ),
        migrations.AddField(
            model_name='problem',
            name='problemSet',
            field=models.ForeignKey(related_name='problems', to='classHelper.ProblemSet'),
        ),
    ]
