#!/usr/bin/env python

import os
from urllib import parse

# README 파일의 헤더 부분
HEADER="""# 
# SWEA & 백준 & 프로그래머스 문제 풀이 목록

[README 자동화 코드 참고](https://velog.io/@betterfuture4/%EA%B9%83%ED%97%88%EB%B8%8C-%EC%9E%94%EB%94%94%EC%97%90-%EB%AC%BC-%EC%A3%BC%EA%B8%B0feat.BaekjoonHub%EB%A1%9C-%EB%B0%B1%EC%A4%80-%EB%AC%B8%EC%A0%9C-%EC%9E%90%EB%8F%99-%EC%97%85%EB%A1%9C%EB%93%9C)
"""

def main():
    content = ""
    content += HEADER  # 헤더 부분 추가
    
    directories = []  # 디렉토리 목록
    solveds = []  # 푼 문제 목록

    for root, dirs, files in os.walk("."):  # 현재 디렉토리부터 시작하여 모든 하위 디렉토리와 파일 순회
        dirs.sort()  # 디렉토리 정렬
        if root == '.':  # 현재 디렉토리일 경우
            for dir in ('.git', '.github'):  # .git, .github 디렉토리는 제외
                try:
                    dirs.remove(dir)
                except ValueError:
                    pass
            continue

        category = os.path.basename(root)  # 현재 디렉토리명을 카테고리로 사용

        if category == 'images':  # images 디렉토리는 제외
            continue

        directory = os.path.basename(os.path.dirname(root))  # 부모 디렉토리명을 카테고리로 사용

        if directory == '.':  # 부모 디렉토리가 없는 경우 제외
            continue
            
        # 디렉토리명이 없는 경우 추가
        if directory not in directories:
            if directory in ["백준", "프로그래머스", "SWEA"]:  # 백준, 프로그래머스, SWEA인 경우 제목 설정
                content += "## 📚 {}\n".format(directory)
            else:
                content += "### 🚀 {}\n".format(directory)  # 그 외의 경우 제목 설정
                content += "| 문제번호 | 링크 |\n"
                content += "| ----- | ----- |\n"
            directories.append(directory)

        # SWEA의 경우에만 추가적인 처리
        if category == "SWEA":
            content += "### 🚀 {}\n".format(category)
            content += "| 문제번호 | 링크 |\n"
            content += "| ----- | ----- |\n"
            
        # 파일 순회하며 문제 정보 추가
        for file in files:
            if category not in solveds:
                if directory == "SWEA":  # SWEA인 경우 특별한 처리
                    content += "|{}|[링크]({})|\n".format(os.path.splitext(file)[0], parse.quote(os.path.join(root, file)))
                else:
                    content += "|{}|[링크]({})|\n".format(category, parse.quote(os.path.join(root, file)))
                solveds.append(category)

    totalStr = "\n\n 🧨Total Solved Problems: " + str(len(solveds))  # 푼 문제의 총 개수 출력
    content += totalStr  # 총 문제 개수 추가

    with open("README.md", "w") as fd:
        fd.write(content)  # README.md 파일에 내용 쓰기
        
if __name__ == "__main__":
    main()
