
googleCloudRun:
	gcloud builds submit --tag gcr.io/PROJECT-ID/helloworld
	gcloud run deploy --image gcr.io/PROJECT-ID/helloworld --platform managed
